import GLOOP.*;
public class Ship {
    GLTafel ship;
    GLVektor vShip;
    Laser[] laser;
    int laserNum;
    int chng = 1;
    int[] ready;
    double width, length;
    double shootrate = 0;
    double border = 250;
    Ship(double pX, double pY, double pZ, double pLength, double pWidth, double pRed, double pGreen, double pBlue, int pLaserNum){
        width = pWidth / 0.93;
        length = pLength;
        vShip = new GLVektor(pX, pY, pZ);
        ship = new GLTafel(vShip, width, length, "src/Ship2D.png");
        ship.setzeSelbstleuchten(1, 1, 1);
        ship.skaliere(1.25, 1, 1);
        ship.setzeAutodrehung(true);
        laserNum = pLaserNum;
        laser = new Laser[laserNum];
        ready = new int[2];
        for(int i = 0; i<laserNum; i++){
            laser[i] = new Laser();
            laser[i].load(100, 100, 2.5, 10);
        }
        //laser[1].getReady(1, 1);
    }


    public double getX(){return ship.gibX();}
    public double getY(){return ship.gibY();}
    public double getZ(){return ship.gibZ();}
    public GLVektor getPos(){return vShip;}
    public void moveRight(double pSpeed){
        if(border > this.getX() + width/2){
            vShip.x = vShip.x + pSpeed;
            ship.setzePosition(vShip);
        }
    }
    public void moveLeft(double pSpeed){
        if(-border < this.getX() - width/2){
            vShip.x = vShip.x - pSpeed;
            ship.setzePosition(vShip);
        }
    }
    public void getKnowlage(Laser pLaser, int i){
        laser[i] = pLaser;
    }
    public void shoot(double rate_in_shots_per_second){
        if(shootrate == 0){
            for(int i = 0; i < ready.length; i++){
                if(!laser[i].ready){
                    laser[i].ready = true;
                    System.out.println(laser[i].ready + " | " + i);
                    break;
                }
                else{
                    System.out.println("ready" + " | " + i);
                }
            }
        }
        shootrate = 1000/rate_in_shots_per_second;

        for(int i = 0; i < laserNum; i++){
        System.out.println(laser[i].ready);
        }
    }

    public void fly(){
        for(int i = 0; i < laserNum; i++){
            laser[i].shoot(0.1);
        }
    }
    public void timer(){
        if(shootrate>0){
            shootrate--;
            System.out.println(shootrate);
        }
    }
}
