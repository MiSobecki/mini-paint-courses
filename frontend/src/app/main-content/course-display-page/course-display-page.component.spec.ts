import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseDisplayPageComponent } from './course-display-page.component';

describe('CourseDisplayPageComponent', () => {
  let component: CourseDisplayPageComponent;
  let fixture: ComponentFixture<CourseDisplayPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseDisplayPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseDisplayPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
