package basic;

// Thread

class PlayGame extends Thread {
	
	public boolean play;
	
	public PlayGame() {
	}
	
	@Override
	public void run() {
		this.play = true;
		while(this.play) {
			System.out.println("게임 하는중");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

class PlayMusic implements Runnable{
	
	public boolean play;

	@Override
	public void run() {
		this.play = true;
		while(this.play) {
			System.out.println("음악소리");
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}


public class Ex_002_쓰레드 {

	public static void main(String[] args) {
		PlayGame play = new PlayGame();
//		play.run(); // 이렇게 실행도 가능하지만
//		play.start(); // 보통 start로 실행
//		
//		for(int n= 0; n < 10; n ++) {
//			System.out.println("n : " + n);
//			if(n == 8) {
//				System.out.println("등장");
//				play.stop(); // 상위 버전에서는 사용안함
//				play.play = false; // play.stop();
//			}
//			try {
//				Thread.sleep(1000);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
		
//		PlayMusic que = new PlayMusic();
//		que.run();
		
		Runnable music = new PlayMusic();
		Thread thread = new Thread(music);
		thread.start();
		
		for(int n= 0; n < 10; n ++) {
			System.out.println("n : " + n);
			if(n == 7) {
				System.out.println("등장");
				// ? stop() 쓰지 않고 -> 스레드 종료
				// music 이 PlayMusic으로 형변환이 가능하면 true
				if(music instanceof PlayMusic) {
					PlayMusic stop = (PlayMusic) music;
					stop.play = false;
				}
				
//				try {
//					PlayMusic stop = (PlayMusic) music;
//					stop.play = false;
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
