import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseStepDisplayComponent } from './course-step-display.component';

describe('CourseStepDisplayComponent', () => {
  let component: CourseStepDisplayComponent;
  let fixture: ComponentFixture<CourseStepDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseStepDisplayComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseStepDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
