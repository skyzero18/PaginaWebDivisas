import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Divisa } from '../divisa-table/divisa.model'; // crea esta interfaz con los campos que usas

@Component({
  selector: 'app-divisa-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './divisa-table.html',
  styleUrls: ['./divisa-table.css']
})
export class DivisaTable {
  @Input() divisas: Divisa[] = [];
  cargando = false;

}
