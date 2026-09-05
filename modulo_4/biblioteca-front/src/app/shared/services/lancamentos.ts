import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
// libs
import { Observable } from 'rxjs';
// services
import { DaoService } from './dao.service';
// models
import { Lancamento } from '../models/lancamento';
import { OperacaoTypeEnum } from '../enums/operacao-type.enum';

@Injectable({
  providedIn: 'root',
})
export class Lancamentos {

  private lancamentoSelecionado: Lancamento = {} as Lancamento;

  private API_LANCAMENTO = '/api/lancamento';

  constructor(
    private daoService: DaoService
  ){}

  get modoEdicao(): boolean {
    return (sessionStorage.getItem('modoEdicao') === OperacaoTypeEnum.EDITAR);
  }

  set modoEdicao(ehEdicao: boolean) {
    if (ehEdicao) {
      sessionStorage.setItem('modoEdicao', OperacaoTypeEnum.EDITAR);
    } else {
      sessionStorage.setItem('modoEdicao', OperacaoTypeEnum.SALVAR);
    }
  }

  /**
   * Grava o lancamento selecionado
   * @param lancamento instancia de um lancamento
   * @returns retorna objeto lancamento selecionada
   */
  gravaLancamentoSelecionado(lancamento: Lancamento): void {
    if (lancamento) {
      this.lancamentoSelecionado = lancamento;   }
  }

  /**
   * Remover o lancamento selecionado do sessão colocando um lancamento vazio
   */
  limparLancamentoSelecionado(): void {
    this.lancamentoSelecionado = {} as Lancamento;
  }

  /**
   * Recupera o lancamento selecionado 
   * @returns retorna objeto lancamento selecionada
   */
  recuperaLancamentoSelecionado(): Lancamento {
    
    if (!this.lancamentoSelecionado.id) {
      return null as unknown as Lancamento;
    }    
    return this.lancamentoSelecionado;
  }

  /**
   * Listar lancamentos existentes
   * @returns Listar lancamentos 
   */
  listarLancamentos(): Observable<HttpResponse<Lancamento[]>> {
    return this.daoService.get<Lancamento[]>(this.API_LANCAMENTO,DaoService.MEDIA_TYPE_APP_JSON);
  }

  /**
   * Criar uma novo lancamento
   * @param lancamento instancia de um lancamento
   * @return retorna objeto lancamento criada
   */
  criarLancamento(lancamento: Lancamento): Observable<HttpResponse<Lancamento>> {
    return this.daoService.post<Lancamento>(this.API_LANCAMENTO, lancamento, DaoService.MEDIA_TYPE_APP_JSON);
  }

  /**
   * Atualiza um lancamento existente na base
   * @param lancamento instancia de um lancamento
   * @returns retorna objeto lancamento alterada
   */
  atualizarLancamento(lancamento: Lancamento): Observable<HttpResponse<Lancamento>> {
    return this.daoService.put<Lancamento>(`${this.API_LANCAMENTO}/${lancamento.id}`, lancamento, DaoService.MEDIA_TYPE_APP_JSON);
  }

  /**
   * Recupera os dados de um Lancamento
   * @param id identificador do lancamento
   * @returns retorna objeto lancamento existente
   */
  obterLancamento(id:number): Observable<HttpResponse<Lancamento>>{
    return this.daoService.get<Lancamento>(`${this.API_LANCAMENTO}/${id}`, DaoService.MEDIA_TYPE_APP_JSON);
  }

  /**
   * Remove lancamento da base
   * @param id identificador do lancamento
   * @returns retorna objeto lancamento excluido
   */
  removerLancamento(id: number): Observable<HttpResponse<Lancamento>> {
    return this.daoService.delete<Lancamento>(`${this.API_LANCAMENTO}/${id}`, DaoService.MEDIA_TYPE_APP_JSON);
  }    

}
