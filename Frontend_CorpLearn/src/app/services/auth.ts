import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // En la version actual de Angular se prefiere usar inject() en lugar de declararlo en el constructor
  private http = inject(HttpClient);

  // Registrar un nuevo usuario
  registrar(usuario: any): Observable<any> {
    return this.http.post(environment.urlRegister, usuario);
  }

  // Autenticar usuario y guardar sesión
  login(credenciales: { email: string; password: string }): Observable<any> {
    return this.http.post<any>(environment.urlLogin, credenciales).pipe(
      tap(response => {
        // Si el backend responde con éxito, guardamos el token y el ID del usuario
        if (response && response.token) {
          localStorage.setItem('token', response.token);
          localStorage.setItem('usuarioId', response.usuarioId || '1'); // Salvaguarda por si el ID viene numérico
          localStorage.setItem('nombre', response.nombre || 'Usuario');
        }
      })
    );
  }

  // Métodos de utilidad para el estado de la sesión
  obtenerToken(): string | null {
    return localStorage.getItem('token');
  }

  obtenerUsuarioId(): number {
    const id = localStorage.getItem('usuarioId');
    return id ? parseInt(id, 10) : 0;
  }

  estaLogueado(): boolean {
    return !!this.obtenerToken();
  }

  cerrarSesion(): void {
    localStorage.clear();
  }
}
