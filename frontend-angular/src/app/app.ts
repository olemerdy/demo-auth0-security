import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { YouTubePlayer } from '@angular/youtube-player';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatButtonModule, MatCardModule, RouterOutlet, YouTubePlayer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend-angular');
}
