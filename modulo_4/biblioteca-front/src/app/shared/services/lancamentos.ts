import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lancamento } from '../models/lancamento';
import { DaoService } from './dao.service';

@Injectable({
  providedIn: 'root',
})
export class Lancamentos {

  constructor(
    private daoService: DaoService
  ){}

  listarLancamentos(): Observable<HttpResponse<Lancamento[]>> {
    return this.daoService.get<Lancamento[]>('/api/lancamento',DaoService.MEDIA_TYPE_APP_JSON);
  }


}
