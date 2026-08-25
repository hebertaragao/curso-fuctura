import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

import { routes } from './app.routes';
import { MenuService } from './shared/services/menu-service';
import { Autenticador } from './shared/services/autenticador';
import { AppState } from './app.state';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(),    
    provideRouter(routes),
    AppState,
    MenuService,
    Autenticador
  ]
};
