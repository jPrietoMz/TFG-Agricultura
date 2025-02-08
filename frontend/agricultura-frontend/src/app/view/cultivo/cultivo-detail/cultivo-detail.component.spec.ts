import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CultivoDetailComponent } from './cultivo-detail.component';

describe('CultivoDetailComponent', () => {
  let component: CultivoDetailComponent;
  let fixture: ComponentFixture<CultivoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CultivoDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CultivoDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
