import { Injectable } from '@angular/core';
import api from '../axiosInstance';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  async getUsers(): Promise<Usuario[]> {
    const response = await api.get('/users');
    return response.data;
  }

  async createUser(usuario: Usuario): Promise<void> {
    await api.post('/users', usuario);
  }

  async deleteUser(id: number): Promise<void> {
    await api.delete(`/users/${id}`);
  }

  async updateUser(id: number, usuario: Usuario): Promise<void> {
    await api.put(`/users/${id}`, usuario);
  }
}
