import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CourseService} from "../../../shared/service/course.service";
import {Course} from "../../../shared/model/course";
import {PaintService} from "../../../shared/service/paint.service";
import {PaintingTechniqueService} from "../../../shared/service/painting-technique.service";
import {ModelingProductService} from "../../../shared/service/modeling-product.service";

@Component({
  selector: 'app-course-display-page',
  templateUrl: './course-display-page.component.html',
  styleUrls: ['./course-display-page.component.scss']
})
export class CourseDisplayPageComponent implements OnInit {

  course?: Course;

  constructor(private route: ActivatedRoute,
              private courseService: CourseService,
              private paintService: PaintService,
              private paintingTechniqueService: PaintingTechniqueService,
              private modelingProductService: ModelingProductService) {
  }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    const courseId = String(routeParams.get('courseId'));

    this.courseService.find(courseId).then((course: Course) => {
      this.course = course;

      this.course.steps = this.course.steps.sort((a, b) => a.orderNumber - b.orderNumber);
    });

    this.paintService.findAll();
    this.paintingTechniqueService.findAll();
    this.modelingProductService.findAll();
  }

}
