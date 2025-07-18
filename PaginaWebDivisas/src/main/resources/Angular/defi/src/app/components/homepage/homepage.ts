// homepage.component.ts
import { Component, OnInit } from '@angular/core';
import { DivisaTable } from '../divisa-table/divisa-table';
import { Conversor } from '../conversor/conversor';
import { Api } from '../../services/api';
import { CommonModule } from '@angular/common'; // Importar CommonModule
import { Divisa } from '../divisa-table/divisa.model';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CommonModule, DivisaTable, Conversor],
  templateUrl: './homepage.html',
  styleUrls: ['./homepage.css']
})
export class Homepage implements OnInit {
  divisas: Divisa[] = [];
  cargando = false;

  constructor(private api: Api) {}

  ngOnInit(): void {
    this.cargarDivisas();
  }

  cargarDivisas() {
    this.cargando = true;
    this.api.get<Divisa[]>('divisas').subscribe({
      next: (data) => {
        // Filtrar solo divisas activas
        this.divisas = data.filter(d => d.status === true);
        this.cargando = false;
      },
      error: (err) => {
        console.error('Error cargando divisas:', err);
        this.cargando = false;
      }
    });
  }
}
