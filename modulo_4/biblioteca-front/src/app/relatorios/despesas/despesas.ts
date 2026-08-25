import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
// modules
import { MaterialModule } from '../../material/material-module';
// components
import { Logout } from '../../shared/components/logout/logout';
import { Menu } from '../../shared/components/menu/menu';
import { Router } from '@angular/router';
import { IDespesa } from '../../shared/models/despesa.interface';

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
  ) {  
    this.listarLancamentos();
    this.iniciarFormulario();    
  }

  /** 
   * carregar as lista de lancamentos (Recitas e Despesas)
   */
  private listarLancamentos(): void {

  }
  
  /** 
   * iniciar formmulario
   */
  private iniciarFormulario(): void {
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
  }  

  /**
   * Método que responde a um evento para editar a despesa
   * @param item instancia do objeto despesa
   */
  onEditar(item: IDespesa): void {
  }  

  /**
   * Método que respondne ao evento de pesquisar
   */
  onPequisar(): void {
  }  
}
