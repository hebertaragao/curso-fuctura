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
  
  constructor(lancamento: IDespesa | IReceita, ehReceita: boolean) {
    this.data = lancamento.data;
    this.descricao = lancamento.descricao;
    this.ehFixo = lancamento.ehFixo;
    this.tipo = lancamento.tipo;
    this.valor = lancamento.valor;
    this.ehReceita = ehReceita;
    
    if (lancamento.id) { this.id = lancamento.id; }
  }

  static toDespesaOrReceita(lancamento: Lancamento): IDespesa | IReceita {
    return {
      data: lancamento.data,
      descricao: lancamento.descricao,
      ehFixo: lancamento.ehFixo,      
      tipo: lancamento.tipo,
      valor: lancamento.valor,
      id: lancamento.id!
    };
  }

}