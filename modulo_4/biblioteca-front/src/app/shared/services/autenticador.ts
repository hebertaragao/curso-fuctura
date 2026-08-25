import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// libs
import { Observable } from 'rxjs';
// services
import { AppState } from '../../app.state';
import { DaoService } from './dao.service';
// models
import { ILogin } from '../models/login.interface';

@Injectable({
  providedIn: 'root',
})
export class Autenticador {

  constructor(
    private daoService: DaoService,
    private state: AppState,
  ){}

  gravaToken(token: string): void {
    this.state.token = token;
  }

  autenticar(login: ILogin): Observable<HttpResponse<ILogin>> {
    return this.daoService.post<ILogin>('/api/autenticador',login,DaoService.MEDIA_TYPE_APP_JSON);
  }

}
