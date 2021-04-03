import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DelveLadderComponent } from './delve-ladder.component';

describe('DelveLadderComponent', () => {
  let component: DelveLadderComponent;
  let fixture: ComponentFixture<DelveLadderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DelveLadderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DelveLadderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
