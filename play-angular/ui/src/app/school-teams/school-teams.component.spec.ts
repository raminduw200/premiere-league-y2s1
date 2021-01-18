import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchoolTeamsComponent } from './school-teams.component';

describe('SchoolTeamsComponent', () => {
  let component: SchoolTeamsComponent;
  let fixture: ComponentFixture<SchoolTeamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SchoolTeamsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SchoolTeamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
