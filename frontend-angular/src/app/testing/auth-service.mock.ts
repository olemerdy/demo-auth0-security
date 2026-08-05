import { Provider } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { of } from 'rxjs';
import { vi } from 'vitest';

export const mockAuthService = {
  isAuthenticated$: of(true),
  isLoading$: of(false),
  user$: of({ name: 'Test User', email: 'test@example.com' }),
  loginWithRedirect: vi.fn().mockResolvedValue(undefined),
  logout: vi.fn().mockResolvedValue(undefined),
  getAccessTokenSilently: vi.fn().mockReturnValue(of('mock-token')),
};

export const provideMockAuthService = (): Provider => ({
  provide: AuthService,
  useValue: mockAuthService,
});
