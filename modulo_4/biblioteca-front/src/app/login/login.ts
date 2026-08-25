import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
// libs
import Swal from 'sweetalert2';
// services
import { Autenticador } from '../shared/services/autenticador';
// modules
import { MaterialModule } from '../material/material-module';
import { HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-login',
  imports: [
    MaterialModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  formulario!: FormGroup;

  constructor(
    private router: Router,  
    private formBuilder: FormBuilder,
    private autenticador: Autenticador  
  ){
    this.initFormulario()
  }


  private initFormulario(): void {
    this.formulario = this.formBuilder.group({
      email: ['', [Validators.email, Validators.required]],
      senha: ['', [Validators.minLength(3), Validators.required]]
    });
  }

  private autenticar(): void {
    const login = this.formulario.value;

    this.autenticador.autenticar(login).subscribe({
      next: (resp) => {
        if(resp.status === HttpStatusCode.Created){
          const token = resp.headers.get('authorization');
          if (token) {
            this.autenticador.gravaToken(token);
          }
        }
        this.router.navigate(['dashboard']);
      },
      error: (err) => {
        if (err.status === HttpStatusCode.NotFound){
          Swal.fire({
          title: "Acesso Negado",
          text: err.error.mensagem,
          icon: "warning"
        });
        } else {
          Swal.fire({
            title: "Acesso",
            text: "Ocorreu um erro. " + err.error.mensagem,
            icon: "error"
          });
        }
      }
    });
  }

  onLogin(): void {
    this.autenticar();
    
  }

}
