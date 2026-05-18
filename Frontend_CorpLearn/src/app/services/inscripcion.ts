import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InscripcionService {
  private http = inject(HttpClient);

  // Inscribir al alumno logueado en un curso
  inscribir(usuarioId: number, cursoId: number): Observable<any> {
    const body = { usuarioId, cursoId };
    return this.http.post<any>(environment.urlInscripcion, body);
  }

  // Obtener el historial de inscripciones de un alumno específico
  listarPorUsuario(usuarioId: number): Observable<any[]> {
    return this.http.get<any[]>(`${environment.urlInscripcion}/usuario/${usuarioId}`);
  }


  // Cancelar o dar de baja una inscripción
  cancelarInscripcion(id: number): Observable<any> {
    return this.http.delete<any>(`${environment.urlInscripcion}/${id}`);
  }
}
