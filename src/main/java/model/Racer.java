package model;

import java.time.Duration;
import java.util.Objects;

public final class Racer implements Comparable<Racer> {

    private final String name;
    private final String team;
    private final Duration bestLap;

    public Racer(String name, String team, Duration bestLap) {
        this.name = name;
        this.team = team;
        this.bestLap = bestLap;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public Duration getBestLap() {
        return bestLap;
    }

    @Override
    public int compareTo(Racer o) {
        return this.bestLap.compareTo(o.bestLap);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Racer racer = (Racer) o;

        if (name == racer.name || (name != null && name.equals(racer.getName()))) return true;
        if (team == racer.team || (team != null && team.equals(racer.getTeam()))) return true;
        return bestLap == racer.bestLap || (bestLap != null && bestLap.equals(racer.getBestLap()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, bestLap);
    }

    @Override
    public String toString() {
        return "Racer{" +
                "name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", bestLap=" + bestLap +
                '}';
    }
}