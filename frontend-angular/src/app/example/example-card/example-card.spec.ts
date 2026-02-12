import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExampleCard } from './example-card';

describe('ExampleCard', () => {
  let component: ExampleCard;
  let fixture: ComponentFixture<ExampleCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExampleCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExampleCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
