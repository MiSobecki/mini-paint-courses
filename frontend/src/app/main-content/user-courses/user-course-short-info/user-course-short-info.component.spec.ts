import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCourseShortInfoComponent } from './user-course-short-info.component';

describe('UserCourseShortInfoComponent', () => {
  let component: UserCourseShortInfoComponent;
  let fixture: ComponentFixture<UserCourseShortInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserCourseShortInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserCourseShortInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
