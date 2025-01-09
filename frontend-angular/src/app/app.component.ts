import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { YouTubePlayer } from '@angular/youtube-player';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, YouTubePlayer],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend-angular';
}
