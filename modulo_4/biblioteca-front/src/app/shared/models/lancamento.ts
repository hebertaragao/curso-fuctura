import { IDespesa } from "./despesa.interface";
import { IReceita } from "./receita.interface";

export class Lancamento {
  data: string;
  descricao: string;
  ehFixo: boolean;
  ehReceita: boolean;
  id?: number;
  mensagem?: string;
  tipo: string;
  valor: number;
  
  constructor(lancamento: IDespesa | IReceita) {
    this.data = lancamento.data;
    this.descricao = lancamento.descricao;
    this.ehFixo = lancamento.ehFixo;
    this.tipo = lancamento.tipo;
    this.valor = lancamento.valor;
    
    if (lancamento.id) { this.id = lancamento.id;}
    this.ehReceita = true; //lancamento instanceof IDespesa ? false : true;
  }


}