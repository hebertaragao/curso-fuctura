import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
// momdules
import { MaterialModule } from '../material/material-module';
// components
import { Menu } from '../shared/components/menu/menu';
import { Logout } from '../shared/components/logout/logout';
// models
import { IDespesa } from '../shared/models/despesa.interface';
import { IReceita } from '../shared/models/receita.interface';
import { Lancamentos } from '../shared/services/lancamentos';
import { Lancamento } from '../shared/models/lancamento';

@Component({
  selector: 'app-dashboard',
  imports: [
    Menu,
    Logout,
    CommonModule,
    MaterialModule,
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard {
  dataSourceDespesas: IDespesa[] = [];
  dataSourceReceitas: IReceita[] = [];
  displayedColumns = ['data','valor','tipo','fixo','descricao','acoes'];

  constructor(
    private router: Router,
    private cdr: ChangeDetectorRef,
    private lancamentosService: Lancamentos
  ) {
    this.listarLancamentos();
  }  

  /** 
   * carregar as lista de lancamentos (Recitas e Despesas)
   * @return void
   */
  private listarLancamentos(): void {
    this.lancamentosService.listarLancamentos().subscribe({
      next: (resp) => {
        const lancamentos:Lancamento[] | null = resp.body;

        this.dataSourceReceitas = lancamentos ? lancamentos
          .filter( (lanc) => lanc.ehReceita === true)
          .map( lanc => Lancamento.toDespesaOrReceita(lanc)) : [];
        this.dataSourceDespesas = lancamentos ? lancamentos
          .filter( (lanc) => lanc.ehReceita === false)
          .map( lanc => Lancamento.toDespesaOrReceita(lanc)) : [];  

        this.cdr.detectChanges();  
      }
    });
  }

  /**
   * Remover despesa da base
   * @param id numero do lancamento
   * @return void
   */
  private removerDespesa(id: number): void {
  }

  /**
   * Remover receita da base
   * @param id numero do lancamento
   * @return void
   */
  private removerReceita(id: number): void { 
  }

  /**
   * Método que responde a um evento para remover a despesa da base
   * @param despesa instancia do objeto despesa
   */
  onRemoverDespesa(despesa: IDespesa): void {
  }

  /**
   * Método que responde a um evento para editar a despesa
   * @param item instancia do objeto lancamento
   */
  onEditDespesa(item: any): void {
  }

    /**
   * Método que responde a um evento para remover a receita
   * @param receita instancia do objeto receita
   */
  onRemoverReceita(receita: IReceita): void {
  }

    /**
   * Método que responde a um evento para editar a receita
   * @param item instancia do objeto lancamento
   */  
  onEditReceita(item: any): void {

  }
}
