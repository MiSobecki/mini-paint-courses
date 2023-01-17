import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Observable, of} from "rxjs";
import {Paint} from "../../../shared/model/paint";
import {PaintingTechnique} from "../../../shared/model/painting-technique";
import {ModelingProduct} from "../../../shared/model/modeling-product";
import {PaintService} from "../../../shared/service/paint.service";
import {PaintingTechniqueService} from "../../../shared/service/painting-technique.service";
import {CourseService} from "../../../shared/service/course.service";
import {ModelingProductService} from "../../../shared/service/modeling-product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Course} from "../../../shared/model/course";
import {STEPPER_GLOBAL_OPTIONS} from "@angular/cdk/stepper";
import {CourseUpdateDto} from "../../../shared/model/course-update-dto";
import {CourseStep} from "../../../shared/model/course-step";
import {CourseStepImagesInfo} from "../../../shared/model/course-step-images-info";
import {ImageService} from "../../../shared/service/image.service";
import {CourseStepImage} from "../../../shared/model/course-step-image";

@Component({
  selector: 'app-course-update-page',
  templateUrl: './course-update-page.component.html',
  styleUrls: ['./course-update-page.component.scss'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: {showError: true},
    },
  ],
})
export class CourseUpdatePageComponent implements OnInit {

  basicInfoFormGroup: FormGroup;
  stepsFormGroup: FormGroup;
  paints$: Observable<Paint[]> = of([]);
  paintingTechniques$: Observable<PaintingTechnique[]> = of([]);
  modelingProducts$: Observable<ModelingProduct[]> = of([]);

  course?: Course;

  isLoaded = false;

  courseStepImageInfos: CourseStepImagesInfo[] = [];

  uploadedImages: CourseStepImage[] = [];

  constructor(private formBuilder: FormBuilder,
              private paintService: PaintService,
              private paintingTechniqueService: PaintingTechniqueService,
              private courseService: CourseService,
              private modelingProductService: ModelingProductService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private imageService: ImageService) {
    this.basicInfoFormGroup = this.formBuilder.group({});
    this.stepsFormGroup = this.formBuilder.group({});
  }

  ngOnInit(): void {
    this.paintService.findAll();
    this.paints$ = this.paintService.paints$;

    this.paintingTechniqueService.findAll();
    this.paintingTechniques$ = this.paintingTechniqueService.paintingTechniques$;

    this.modelingProductService.findAll();
    this.modelingProducts$ = this.modelingProductService.modelingProducts$;

    const routeParams = this.activatedRoute.snapshot.paramMap;
    const courseId = String(routeParams.get('courseId'));

    this.courseService.find(courseId).then((course: Course) => {
      this.course = course;

      this.course.steps = this.course.steps.sort((a, b) => a.orderNumber - b.orderNumber);

      this.basicInfoFormGroup = this.formBuilder.group({
        title: [this.course?.title, [Validators.required, Validators.maxLength(100)]],
        shortDescription: [this.course?.shortDescription, [Validators.required, Validators.maxLength(500)]]
      });
      this.stepsFormGroup = this.formBuilder.group({
        steps: this.formBuilder.array(this.createStepsControls(), Validators.required)
      });

      this.isLoaded = true;

      course.steps.forEach(x => this.courseStepImageInfos.push(this.createCourseStepImagesInfo(x.id)));

      this.downloadImages(course);
    });
  }

  get steps(): FormArray {
    return this.stepsFormGroup.get('steps') as FormArray;
  }

  addStep(): void {
    const stepForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(100)]],
      description: ['', [Validators.required, Validators.maxLength(1000)]],
      paints: this.formBuilder.array([]),
      modelingProducts: this.formBuilder.array([])
    });

    this.steps.push(stepForm);
  }

  getPaints(index: number): FormArray {
    const step = this.steps.at(index) as FormGroup;

    return step.get('paints') as FormArray;
  }

  addPaint(index: number): void {
    const stepPaints = this.getPaints(index);

    const paintForm = this.formBuilder.group({
      paint: ['', Validators.required],
      technique: ['', Validators.required]
    });

    stepPaints.push(paintForm);
  }

  getProducts(index: number): FormArray {
    const step = this.steps.at(index) as FormGroup;

    return step.get('modelingProducts') as FormArray;
  }

  addModelingProduct(index: number): void {
    const stepProducts = this.getProducts(index);

    const productForm = new FormControl('', Validators.required);

    stepProducts.push(productForm);
  }

  removePaint(stepIndex: number,
              paintIndex: number): void {
    const paints = this.getPaints(stepIndex);

    paints.removeAt(paintIndex);
  }

  removeProduct(stepIndex: number,
                productIndex: number): void {
    const products = this.getProducts(stepIndex);

    products.removeAt(productIndex);
  }

  onSubmit(): void {
    const basicInfo = this.basicInfoFormGroup.value;

    const stepsInfo = this.stepsFormGroup.value;

    const steps: CourseStep[] = [];
    let index = 1;

    stepsInfo.steps.forEach((x: {
      id: string | null;
      paints: { paint: string; technique: string; }[];
      title: string;
      description: string;
      modelingProducts: string[];
    }) => {
      const paintsMap = Object.create(null);
      x.paints.forEach((paint: { paint: string; technique: string; }) => {
        paintsMap[paint.paint] = paint.technique;
      })

      const step = new CourseStep(x.id, index, x.title, x.description, paintsMap, x.modelingProducts);

      steps.push(step);

      index += 1;
    });

    const courseUpdateDto = new CourseUpdateDto(basicInfo.title, basicInfo.shortDescription, steps);

    this.uploadImages();

    if (this.course?.id) {
      this.courseService.update(courseUpdateDto, this.course.id)
    }
  }

  onCancel(): void {
    this.router.navigate(['/user-courses']).finally();
  }

  onStepRemoval(index: number): void {
    this.steps.removeAt(index);
  }

  getCourseStepImages(index: number): CourseStepImage[] {
    const step = this.steps.at(index) as FormGroup;

    return this.uploadedImages.filter(x => x.courseStepId === step.get('id')?.value);
  }

  private createCourseStepImagesInfo(courseStepId: string): CourseStepImagesInfo {
    const info = new CourseStepImagesInfo();
    info.courseStepId = courseStepId;

    return info;
  }

  private createStepsControls(): FormGroup[] {
    if (this.course) {
      return this.course?.steps.map(x => this.formBuilder.group({
        id: [x.id],
        title: [x.title, [Validators.required, Validators.maxLength(100)]],
        description: [x.description, [Validators.required, Validators.maxLength(1000)]],
        paints: this.formBuilder.array([...x.paintTechniqueIdToPaintIdMap].map(([key, value]) => this.formBuilder.group({
          paint: [key, Validators.required],
          technique: [value, Validators.required]
        }))),
        modelingProducts: this.formBuilder.array(x.usedOtherModelingProductIds.map(product => [product, Validators.required]))
      }));
    } else {
      return [];
    }
  }

  private uploadImages(): void {
    this.courseStepImageInfos.forEach(stepImages => {

      if (stepImages.selectedFiles) {
        for (let i = 0; i < stepImages.selectedFiles.length; i++) {
          this.imageService.upload(stepImages.selectedFiles[i], stepImages.courseStepId);
        }
      }
    })
  }

  private downloadImages(course: Course) {
    course.steps.forEach(x => {
      this.imageService.download(x.id).then(images => {
        images.forEach(image => {
          this.uploadedImages.push(image);
        });
      });
    });
  }

}
