import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class RegisterComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  registerForm: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';
  loading: boolean = false;

  constructor() {
    this.registerForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(3)]],
      apellido: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  onSubmit(): void {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    // Estructuramos el JSON tal cual lo espera el register de Spring Boot
    const nuevoUsuario = {
      nombre: this.registerForm.value.nombre,
      apellido: this.registerForm.value.apellido,
      email: this.registerForm.value.email,
      password: this.registerForm.value.password,
      rol: {
        id: 1 // Por defecto se registra como Cliente
      }
    };

    this.authService.registrar(nuevoUsuario).subscribe({
      next: (response) => {
        this.loading = false;
        this.successMessage = '¡Cuenta creada con éxito! Redirigiéndote al login...';
        // Esperamos 2 segundos para que el Usuario vea el mensaje de éxito antes de mandarlo a loguearse
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (err) => {
        this.loading = false;
        if (err.error && err.error.error) {
          this.errorMessage = err.error.error;
        } else {
          this.errorMessage = 'El correo ya se encuentra registrado o el servicio de registro está caído.';
        }
      }
    });
  }
}
