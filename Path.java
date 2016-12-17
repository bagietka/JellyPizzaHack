import java.util.*;
class Path
    {
        public int from;
        public int to;
        public double pheromone;
        public double InvertedLength;
        public int Length;
        public Path(int s, int e, int l,double ph)
        {
            this.from = s;
            this.to = e;
            this.pheromone = ph;
            this.Length = l;
			this.InvertedLength = 1.0 / this.Length; // bugogenne, wcześniej był setter zmieniający dwa!
        }
        public double GetMultiplier()
        {
            return Math.pow(this.pheromone, asrankaco.Alpha) *
                   Math.pow(this.InvertedLength, asrankaco.Beta);
        }
    }