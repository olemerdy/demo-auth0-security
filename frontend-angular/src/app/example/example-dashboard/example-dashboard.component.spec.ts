import { waitForAsync, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExampleDashboardComponent } from './example-dashboard.component';

describe('ExampleDashboardComponent', () => {
  let component: ExampleDashboardComponent;
  let fixture: ComponentFixture<ExampleDashboardComponent>;

  beforeEach(() => {
    fixture = TestBed.createComponent(ExampleDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
