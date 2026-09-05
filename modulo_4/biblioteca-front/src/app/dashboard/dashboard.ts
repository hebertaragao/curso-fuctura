import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
// libs
import Swal from 'sweetalert2';
// services
import { Lancamentos } from '../shared/services/lancamentos';
import { MenuService } from '../shared/services/menu-service';
// modules
import { MaterialModule } from '../material/material-module';
// components
import { Menu } from '../shared/components/menu/menu';
import { Logout } from '../shared/components/logout/logout';
// models
import { IDespesa } from '../shared/models/despesa.interface';
import { IReceita } from '../shared/models/receita.interface';
import { Lancamento } from '../shared/models/lancamento';
import { MenuTypeEnum } from '../shared/enums/menu-type.enum';

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
    private menuService: MenuService,
    private lancamentosService: Lancamentos
  ) {
    this.menuService.ondeEstou = MenuTypeEnum.DASHBOARD;
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
    this.lancamentosService.removerLancamento(id).subscribe({
      next: (response) => {
        if (response.status === HttpStatusCode.Ok) {
          Swal.fire(
            'SUCESSO: Remover Despesa',
            'Despesa removida com sucesso',
            'success'
          )
        }
        this.listarLancamentos();
      },
      error: (err: HttpErrorResponse) => {
        Swal.fire(
          'ALERTA: Remover Despesa',
          err.error.mensagem ? err.error.mensagem : 'Ocorrer um erro inesperado. ['+ err.error.error +']',
          'warning'
        )
      }
    });    
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
    if(despesa) {
      Swal.fire({
        title: 'Remover Despesa',
        text: `Deseja remover a despesa '${despesa.descricao.toUpperCase()}' ?`,
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sim, remova!'
      }).then((resultado) => {
        if (resultado.isConfirmed) {
          const id = despesa.id ? despesa.id : 0;
          this.removerDespesa(id);
        }
      });
    }    
  }

  /**
   * Método que responde a um evento para editar a despesa
   * @param item instancia do objeto lancamento
   */
  onEditDespesa(item: any): void {
    if(item) {
      this.lancamentosService.modoEdicao = true;
      this.lancamentosService.gravaLancamentoSelecionado(item);
      this.router.navigate(['lancamentos/despesa/'+item.id]);
    }    
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
