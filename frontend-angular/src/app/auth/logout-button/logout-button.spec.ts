import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogoutButton } from './logout-button';
import {provideMockAuthService} from '../../testing/auth-service.mock';

describe('LogoutButton', () => {
  let component: LogoutButton;
  let fixture: ComponentFixture<LogoutButton>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LogoutButton],
      providers: [
        provideMockAuthService(),
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogoutButton);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
