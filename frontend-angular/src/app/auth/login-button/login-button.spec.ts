import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginButton } from './login-button';
import { provideMockAuthService } from '../../testing/auth-service.mock';

describe('LoginButton', () => {
  let component: LoginButton;
  let fixture: ComponentFixture<LoginButton>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginButton],
      providers: [
        provideMockAuthService(),
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginButton);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
