import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  // Agregamos ReactiveFormsModule para los formularios y CommonModule para directivas como *ngIf
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  loginForm: FormGroup;
  errorMessage: string = '';
  loading: boolean = false;

  constructor() {
    // Definimos los campos del formulario con sus reglas de validación
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  // Método que se ejecuta al presionar "Ingresar"
  onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.authService.login(this.loginForm.value).subscribe({
      next: (response) => {
        this.loading = false;
        // Si el login es exitoso, el servicio guarda el token y redirigimos al catálogo
        this.router.navigate(['/cursos']);
      },
      error: (err) => {
        this.loading = false;
        // Capturamos el error estructurado del backend (ej: "Credenciales incorrectas")
        if (err.error && err.error.error) {
          this.errorMessage = err.error.error;
        } else {
          this.errorMessage = 'Ocurrió un error al intentar iniciar sesión. Inténtalo más tarde.';
        }
      }
    });
  }
}