import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CursoService {
  private http = inject(HttpClient);

  // Obtener todos los cursos
  listarCursos(): Observable<any[]> {
    return this.http.get<any[]>(environment.urlCurso);
  }

  // Obtener un solo curso por su ID
  buscarCurso(id: number): Observable<any> {
    return this.http.get<any>(`${environment.urlCurso}/${id}`);
  }

  // Crear un nuevo curso
  crearCurso(curso: any): Observable<any> {
    return this.http.post<any>(environment.urlCurso, curso);
  }

  // Modificar un curso existente
  modificarCurso(id: number, cursoDetalles: any): Observable<any> {
    return this.http.put<any>(`${environment.urlCurso}/${id}`, cursoDetalles);
  }

  // Eliminar un curso del sistema
  eliminarCurso(id: number): Observable<any> {
    return this.http.delete<any>(`${environment.urlCurso}/${id}`);
  }
}
