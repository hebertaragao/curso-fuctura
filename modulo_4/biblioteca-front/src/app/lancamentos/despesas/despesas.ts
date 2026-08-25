import { ChangeDetectorRef, Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
// modules
import { MaterialModule } from '../../material/material-module';
// services 
import { MenuService } from '../../shared/services/menu-service';
// components
import { Menu } from '../../shared/components/menu/menu';
import { Logout } from '../../shared/components/logout/logout';
// models
import { IDespesa } from '../../shared/models/despesa.interface';
// enums
import { MenuTypeEnum } from '../../shared/enums/menu-type.enum';

@Component({
  selector: 'app-despesas',
  imports: [
    Menu,
    Logout,
    MaterialModule,
    ReactiveFormsModule,
  ],
  templateUrl: './despesas.html',
  styleUrl: './despesas.scss',
})
export class Despesas {
  private idEdicao = 0;
  public formulario!: FormGroup;

  tipos: string[] = ['Alimentação', 'Habitacão', 'Transporte', 'Saúde', 'Educação', 'Lazer', 'Outros'];

  constructor(
    private cdr: ChangeDetectorRef,
    private formBuilder: FormBuilder,    
    private activeRouter: ActivatedRoute,
    private menuService: MenuService
  ){
    this.menuService.ondeEstou = MenuTypeEnum.LANCAMENTO_DESPESA;
    this.listarDespesas();
    this.iniciarFormulario();

    const id = this.activeRouter.snapshot.params['id'];
    if (id) {
      this.idEdicao = id;
      this.verificarModoEdicao();
    } else {
      // modo edicao
    }
  }

  get buttonLabel(): string {
    return 'Salvar';
  }

  /**
   * Iniciar criação do formulario
   */
  private iniciarFormulario(): void {
  }

  /**
   * Verifica se está no modo edição
   */
  private verificarModoEdicao(): void {
  }

  /** 
   * carregar as lista de despesas 
   */
  private listarDespesas(): void {
  }  

  /**
   * Carregar o formulario com os dados da despesa
   * @param despesa instacia da despesa
   */
  private carregarFormulario(despesa: IDespesa): void {
  }

  /**
   * Salvar a instancia da despesa
   * @param despesa objeto instanciado
   */
  private salvar(despesa: IDespesa): void {
  }

  /**
   * Atualiza a instância da despesa
   * @param despesa objeto instanciado
   */
  private atualizar(despesa: IDespesa): void {
  }
  
  /**
   * Metodo que respode ao evento para salvar
   */
  onSalvar(): void {
  }

  /**
   * Método que responde ao evento de limpar
   */
  onLimpar(): void {
  }

}
