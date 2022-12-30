import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {STEPPER_GLOBAL_OPTIONS} from "@angular/cdk/stepper";
import {GameService} from "../../../shared/service/game.service";
import {GameShortInfo} from "../../../shared/model/game-short-info";
import {Observable, of} from "rxjs";
import {FactionService} from "../../../shared/service/faction.service";
import {FactionShortInfo} from "../../../shared/model/faction-short-info";
import {MiniatureShortInfo} from "../../../shared/model/miniature-short-info";
import {MiniatureService} from "../../../shared/service/miniature.service";
import {PaintService} from "../../../shared/service/paint.service";
import {PaintingTechniqueService} from "../../../shared/service/painting-technique.service";
import {Paint} from "../../../shared/model/paint";
import {PaintingTechnique} from "../../../shared/model/painting-technique";
import {Course} from "../../../shared/model/course";
import {CourseMiniature} from "../../../shared/model/course-miniature";
import {CourseStep} from "../../../shared/model/course-step";
import {CourseService} from "../../../shared/service/course.service";
import {ModelingProductService} from "../../../shared/service/modeling-product.service";
import {ModelingProduct} from "../../../shared/model/modeling-product";
import {Router} from "@angular/router";

@Component({
  selector: 'app-course-creation-page',
  templateUrl: './course-creation-page.component.html',
  styleUrls: ['./course-creation-page.component.scss'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: {showError: true},
    },
  ],
})
export class CourseCreationPageComponent implements OnInit {

  basicInfoFormGroup: FormGroup;
  stepsFormGroup: FormGroup;

  games$: Observable<GameShortInfo[]> = of([]);
  factions$: Observable<FactionShortInfo[]> = of([]);
  miniatures$: Observable<MiniatureShortInfo[]> = of([]);
  paints$: Observable<Paint[]> = of([]);
  paintingTechniques$: Observable<PaintingTechnique[]> = of([]);
  modelingProducts$: Observable<ModelingProduct[]> = of([]);

  constructor(private formBuilder: FormBuilder,
              private gameService: GameService,
              private factionService: FactionService,
              private miniatureService: MiniatureService,
              private paintService: PaintService,
              private paintingTechniqueService: PaintingTechniqueService,
              private courseService: CourseService,
              private modelingProductService: ModelingProductService,
              private router: Router) {
    this.basicInfoFormGroup = this.formBuilder.group({});
    this.stepsFormGroup = this.formBuilder.group({});

    this.gameService.findAll();
    this.games$ = this.gameService.gamesShortInfo$;

    this.paintService.findAll();
    this.paints$ = this.paintService.paints$;

    this.paintingTechniqueService.findAll();
    this.paintingTechniques$ = this.paintingTechniqueService.paintingTechniques$;

    this.modelingProductService.findAll();
    this.modelingProducts$ = this.modelingProductService.modelingProducts$;
  }

  ngOnInit(): void {
    this.basicInfoFormGroup = this.formBuilder.group({
      game: ['', Validators.required],
      faction: ['', Validators.required],
      miniature: ['', Validators.required],
      title: ['', [Validators.required, Validators.maxLength(100)]],
      shortDescription: ['', [Validators.required, Validators.maxLength(500)]]
    });
    this.stepsFormGroup = this.formBuilder.group({
      steps: this.formBuilder.array([], Validators.required)
    });
  }

  get gameId(): string {
    return this.basicInfoFormGroup.get('game')?.value;
  }

  onGameSelect(): void {
    const gameId = this.gameId;

    if (gameId) {
      this.factionService.findAllByGame(gameId);
      this.factions$ = this.factionService.factionsShortInfo$;
    }
  }

  get factionId(): string {
    return this.basicInfoFormGroup.get('faction')?.value;
  }

  onFactionSelect(): void {
    const factionId = this.factionId;

    if (factionId) {
      this.miniatureService.findAllByFaction(factionId);
      this.miniatures$ = this.miniatureService.miniaturesShortInfo$;
    }
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

    const miniature = new CourseMiniature(basicInfo.miniature, '', '', '', '');

    const stepsInfo = this.stepsFormGroup.value;

    const steps: CourseStep[] = [];
    let index = 1;

    stepsInfo.steps.forEach((x: {
      paints: { paint: string; technique: string; }[];
      title: string;
      description: string;
      modelingProducts: string[];
    }) => {
      const paintsMap = Object.create(null);
      x.paints.forEach((paint: { paint: string; technique: string; }) => {
        paintsMap[paint.paint] = paint.technique;
      })

      const step = new CourseStep(null, index, x.title, x.description, paintsMap, x.modelingProducts);

      steps.push(step);

      index += 1;
    });

    const course = new Course(null, basicInfo.title, basicInfo.shortDescription, steps, miniature, null);

    this.courseService.create(course);
  }

  onCancel(): void {
    this.router.navigate(['/user-courses']).finally();
  }

  onStepRemoval(index: number): void {
    this.steps.removeAt(index);
  }

}
