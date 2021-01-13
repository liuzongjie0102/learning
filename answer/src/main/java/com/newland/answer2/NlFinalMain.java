package com.newland.answer2;


import java.util.*;
import java.util.stream.Collectors;

public class NlFinalMain {

    private static int count = 0;

    static class Horse{
        private int number;
        private int speed;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }
    }

    public static void main(String[] args) {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 25; i++){
            Horse horse = new Horse();
            horse.setNumber(i);
            horse.setSpeed((int) (Math.random() * 100));
            horses.add(horse);
        }

        for (Horse horse : horses) {
            System.out.println("number:" + horse.getNumber() + ", speed:" + horse.getSpeed());
        }

        List<List<Horse>> racingSessions = new ArrayList<>();

        for (int i = 0; i < horses.size(); i += 5){
            if (i + 5 > horses.size()){
                racingSessions.add(horseRacing(horses.subList(i, horses.size())));
            }else{
                racingSessions.add(horseRacing(horses.subList(i, i + 5)));
            }
        }







        List<Horse> finalsSession = horseRacing(Arrays.asList(racingSessions.get(0).get(0),
                        racingSessions.get(1).get(0),
                        racingSessions.get(2).get(0),
                        racingSessions.get(3).get(0),
                        racingSessions.get(4).get(0)));

        Horse firstHorse = finalsSession.get(0);

        List<Horse> firstTeam = racingSessions.stream().filter(horseList -> {
            for (Horse horse : horseList) {
                if (horse.getNumber() == firstHorse.getNumber()){
                    return true;
                }
            }
            return false;
        }).findFirst().get();

        List<Horse> secondTeam = racingSessions.stream().filter(horseList -> {
            for (Horse horse : horseList) {
                if (horse.getNumber() == finalsSession.get(1).getNumber()){
                    return true;
                }
            }
            return false;
        }).findFirst().get();

        List<Horse> endSession = horseRacing(Arrays.asList(firstTeam.get(1),
                        firstTeam.get(2),
                        secondTeam.get(0),
                        secondTeam.get(1),
                        finalsSession.get(2)));
        Horse secondHorse = endSession.get(0);
        Horse thirdHorse = endSession.get(1);

        System.out.println("一共比了" + count + "次");
        System.out.println("第一名：number " + firstHorse.getNumber() + ", speed " + firstHorse.getSpeed());
        System.out.println("第二名：number " + secondHorse.getNumber() + ", speed " + secondHorse.getSpeed());
        System.out.println("第三名：number " + thirdHorse.getNumber() + ", speed " + thirdHorse.getSpeed());
    }

    private static List<Horse> horseRacing(List<Horse> horses) {
        count++;
        return horses.stream().sorted(Comparator.comparing(Horse::getSpeed).reversed()).collect(Collectors.toList());
    }
}
