import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniTeamsComponent } from './uni-teams.component';

describe('UniTeamsComponent', () => {
  let component: UniTeamsComponent;
  let fixture: ComponentFixture<UniTeamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniTeamsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UniTeamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
