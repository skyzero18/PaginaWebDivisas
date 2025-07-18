import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DivisaTable } from './divisa-table';

describe('DivisaTable', () => {
  let component: DivisaTable;
  let fixture: ComponentFixture<DivisaTable>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DivisaTable]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DivisaTable);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
