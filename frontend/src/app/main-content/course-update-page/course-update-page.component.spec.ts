import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseUpdatePageComponent } from './course-update-page.component';

describe('CourseUpdatePageComponent', () => {
  let component: CourseUpdatePageComponent;
  let fixture: ComponentFixture<CourseUpdatePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseUpdatePageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseUpdatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
