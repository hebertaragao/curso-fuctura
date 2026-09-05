import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { HttpStatusCode } from '@angular/common/http';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
// libs
import { DateTime } from 'luxon';
// services
import { Lancamentos } from '../../shared/services/lancamentos';
// modules
import { MaterialModule } from '../../material/material-module';
// components
import { Logout } from '../../shared/components/logout/logout';
import { Menu } from '../../shared/components/menu/menu';
// models
import { IDespesa } from '../../shared/models/despesa.interface';
import Swal from 'sweetalert2';
import { Lancamento } from '../../shared/models/lancamento';
import { MenuService } from '../../shared/services/menu-service';
import { MenuTypeEnum } from '../../shared/enums/menu-type.enum';

@Component({
  selector: 'app-despesas',
  imports: [
    Menu, 
    Logout, 
    CommonModule,
    MaterialModule, 
    ReactiveFormsModule
  ],
  templateUrl: './despesas.html',
  styleUrl: './despesas.scss',
})
export class Despesas {
  dataSource: any[] = [];
  displayedColumns = ['data','valor','tipo','fixo','descricao','acoes'];

  formulario!: FormGroup;

  constructor(
    private cdr: ChangeDetectorRef,
    private router: Router,
    private formBuilder: FormBuilder,
    private menuService: MenuService,
    private lancamentosService: Lancamentos
  ) {  
    this.menuService.ondeEstou = MenuTypeEnum.RELATORIO_DESPESA;
    this.listarLancamentos();
    this.iniciarFormulario();    
  }

  /** 
   * carregar as lista de lancamentos (Recitas e Despesas)
   */
  private listarLancamentos(): void {
    this.lancamentosService.listarLancamentos().subscribe({
      next: (response) => {
        if (response.status === HttpStatusCode.Ok) {
          const lancamentos = response.body ? response.body : [];
          const {dataInicial, dataFinal} = this.formulario.value;
          this.dataSource = lancamentos
          .filter(lanc => lanc.ehReceita === false && (
            ( DateTime.fromISO(lanc.data).valueOf() >= DateTime.fromISO(dataInicial).valueOf() &&
              DateTime.fromISO(lanc.data).valueOf() <= DateTime.fromISO(dataFinal).valueOf() ) 
            )  
          )
          .sort((a, b) => DateTime.fromISO(b.data).valueOf() - DateTime.fromISO(a.data).valueOf());
          // garante que o Angular reavalie a view após a mudança
          this.cdr.detectChanges();
        }
      }
    });
  }
  
  /** 
   * iniciar formmulario
   */
  private iniciarFormulario(): void {
    const hoje = DateTime.now().toISO();
    const inicioMes = DateTime.now().startOf('month').toISO();
    this.formulario = this.formBuilder.group({
      dataInicial: inicioMes,
      dataFinal: hoje
    });    
  }  

  /**
   * obter o valor total das despesas
   */
  get valorTotal(): number {
    return this.dataSource.reduce((total: number, lancamento: any) => {
      return total + lancamento.valor;
    }
    , 0);
  }

  /**
   * Método que realiza a remoção da despesa do backend
   * @param id numero identificador da despesa
   */
  private remover(id: number): void { 
    this.lancamentosService.removerLancamento(id).subscribe({
      next: (response) => {
        if (response.status === HttpStatusCode.Ok) {
          Swal.fire(
            'SUCESSO: Remover Despesa',
            'Despesa removida com sucesso',
            'success'
          )
        }
        this.removeItemLista(id);
      },
      error: (err) => {
        Swal.fire(
          'ALERTA: Remover Despesa',
          err.error.mensagem ? err.error.mensagem : 'Ocorrer um erro inesperado. ['+ err.error.error +']',
          'warning'
        )
      }
    });       
  }

  /**
   * Método que remove item da lista
   * @param id numero identificador da despesa
   */
  private removeItemLista(id: number): void {
    this.dataSource = this.dataSource.filter(item => item.id !== id);
  }
  
  /**
   * Método que responde a um evento para remover a despesa da base
   * @param despesa instancia do objeto despesa
   */
  onRemover(despesa: IDespesa): void {
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
          this.remover(id);
        }
      });
    }    
  }  

  /**
   * Método que responde a um evento para editar a despesa
   * @param item instancia do objeto despesa
   */
  onEditar(item: IDespesa): void {
    if(item) {
      this.lancamentosService.modoEdicao = true;
      // this.lancamentoService.sendSelecionada(lancamento);
      this.lancamentosService.gravaLancamentoSelecionado(new Lancamento(item,false));
      this.router.navigate(['lancamentos/despesa/'+item.id]);
    }    
  }  

  /**
   * Método que respondne ao evento de pesquisar
   */
  onPequisar(): void {
    this.listarLancamentos();
  }  
}
