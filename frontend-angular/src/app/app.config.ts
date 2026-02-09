import { ApplicationConfig, provideBrowserGlobalErrorListeners, isDevMode } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideServiceWorker } from '@angular/service-worker';

import { authHttpInterceptorFn, provideAuth0 } from "@auth0/auth0-angular"
import { provideHttpClient, withInterceptors } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideServiceWorker('ngsw-worker.js', {
            enabled: !isDevMode(),
            registrationStrategy: 'registerWhenStable:30000'
          }),
    provideAuth0({
      domain: 'your-domain.auth0.com',
      clientId: 'your-client-id',
      authorizationParams: {
        redirect_uri: window.location.origin,
        audience: 'https://your-api-identifier'
      },
      httpInterceptor: {
        allowedList: [ '/api/*' ]
      }
    }),
    provideHttpClient(withInterceptors([authHttpInterceptorFn]))
  ]
};
