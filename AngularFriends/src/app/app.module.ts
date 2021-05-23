import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateComponent } from './create.component';
import { ChangeComponent } from './change.component';
import { DeleteComponent } from './delete.component';
import { ScoresComponent } from './scores.component';
import { SearchComponent } from './search.component';
import { UsersComponent } from './Users.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateComponent,
    ChangeComponent,
    DeleteComponent,
    ScoresComponent,
    SearchComponent,
    UsersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
