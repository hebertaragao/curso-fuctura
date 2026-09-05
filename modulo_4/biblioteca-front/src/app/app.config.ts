import { ApplicationConfig, LOCALE_ID, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';
// services
import { MenuService } from './shared/services/menu-service';
import { Autenticador } from './shared/services/autenticador';

import { routes } from './app.routes';
import { AppState } from './app.state';

registerLocaleData(localePt, 'pt-BR');

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(),    
    provideRouter(routes),
    // Força a aplicação a usar o pt-BR como local padrão para formatação de datas, números, etc.
    { provide: LOCALE_ID, useValue: 'pt-BR' },
    AppState,
    MenuService,
    Autenticador
  ]
};
