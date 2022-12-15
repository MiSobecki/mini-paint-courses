import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepageCourseShortInfoComponent } from './homepage-course-short-info.component';

describe('HomepageCourseShortInfoComponent', () => {
  let component: HomepageCourseShortInfoComponent;
  let fixture: ComponentFixture<HomepageCourseShortInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomepageCourseShortInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomepageCourseShortInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
