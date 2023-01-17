import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {CourseStep} from "../../../../shared/model/course-step";
import {PaintService} from "../../../../shared/service/paint.service";
import {Paint} from "../../../../shared/model/paint";
import {Subscription} from "rxjs";
import {PaintingTechnique} from "../../../../shared/model/painting-technique";
import {PaintingTechniqueService} from "../../../../shared/service/painting-technique.service";
import {ModelingProduct} from "../../../../shared/model/modeling-product";
import {ModelingProductService} from "../../../../shared/service/modeling-product.service";
import {ImageService} from "../../../../shared/service/image.service";
import {CourseStepImage} from "../../../../shared/model/course-step-image";

@Component({
  selector: 'app-course-step-display',
  templateUrl: './course-step-display.component.html',
  styleUrls: ['./course-step-display.component.scss']
})
export class CourseStepDisplayComponent implements OnInit, OnDestroy {

  private paints: Paint[] = [];
  private paintingTechniques: PaintingTechnique[] = [];
  private modelingProducts: ModelingProduct[] = [];

  private paintsSub?: Subscription;
  private paintingTechniquesSub?: Subscription;
  private modelingProductsSub?: Subscription;

  uploadedImages: CourseStepImage[] = [];

  constructor(private paintService: PaintService,
              private paintingTechniqueService: PaintingTechniqueService,
              private modelingProductService: ModelingProductService,
              private imageService: ImageService) {
  }

  @Input() courseStep?: CourseStep;

  ngOnInit(): void {
    this.paintsSub = this.paintService.paints$.subscribe((paints: Paint[]) => {
      this.paints = paints;
    });

    this.paintingTechniquesSub = this.paintingTechniqueService.paintingTechniques$.subscribe(
      (paintingTechniques: PaintingTechnique[]) => {
        this.paintingTechniques = paintingTechniques;
      });

    this.modelingProductsSub = this.modelingProductService.modelingProducts$.subscribe(
      (modelingProducts: ModelingProduct[]) => {
        this.modelingProducts = modelingProducts;
      });

    this.downloadImages();
  }

  ngOnDestroy(): void {
    this.paintsSub?.unsubscribe();
    this.paintingTechniquesSub?.unsubscribe();
    this.modelingProductsSub?.unsubscribe();
  }

  getPaintName(paintId: string): string {
    return this.paints.find(x => x.id === paintId)?.name || 'Not found paint name';
  }

  getPaintingTechniqueName(paintingTechniqueId: string): string {
    return this.paintingTechniques.find(x => x.id === paintingTechniqueId)?.name || 'Not found painting technique name';
  }

  getModelingProductName(modelingProductId: string): string {
    return this.modelingProducts.find(x => x.id === modelingProductId)?.name || 'Not found modeling product name';
  }

  private downloadImages() {
    if (this.courseStep?.id) {
      this.imageService.download(this.courseStep.id).then(images => {
        images.forEach(image => {
          this.uploadedImages.push(image);
        });
      });
    }
  }

}
