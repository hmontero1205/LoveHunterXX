package quenchTheThirst;

import java.util.ArrayList;

import main.LoveHunterXX;

public class KevinEnemy extends AriqLivingEntity {

	private String direction;

	public KevinEnemy(int x, int y) {
		super(x, y, .5, "resources/qtt/enemyeast.png", (int) (Math.random() * 3) + 2);
	}

	public void tick() {
		if (LoveHunterXX.qtts == null) {
			return;
		}

		GraphNode goal = new GraphNode(null, LoveHunterXX.qtts.getPlayer().getCenterX(),
				LoveHunterXX.qtts.getPlayer().getCenterY());
		for (KevinEntity e : LoveHunterXX.qtts.getEntities()) {
			if (e instanceof AriqAlluringBottle && this.distance(e, AriqAlluringBottle.DISTANCE) && ((AriqAlluringBottle) e).isActive()) {
				goal = new GraphNode(null, e.getCenterX(), e.getCenterY(), e.getWidth(), e.getHeight());
				break;
			}
		}

		GraphNode decision = findShortestPath(goal);
		if (decision == null || decision.getParent() == null) {
			//System.out.println("NO DECISION");
			return;
		}

		while (decision.getParent().getParent() != null) {
			decision = decision.getParent();
		}

		direction = decision.directionToParent;
		this.loadImages("resources/qtt/enemy" + direction + ".png", .5);
		move(direction);
	}

	public GraphNode findShortestPath(GraphNode goal) {
		ArrayList<GraphNode> explored = new ArrayList<>();
		ArrayList<GraphNode> queue = new ArrayList<>();

		GraphNode loc = new GraphNode(goal, this.getCenterX(), this.getCenterY());
		queue.add(loc);

		while (!queue.isEmpty()) {
			GraphNode current = queue.get(0);
			for (GraphNode queued : queue) {
				if (queued.fCost() < current.fCost()
						|| queued.fCost() == current.fCost() && queued.hCost < current.hCost) {
					current = queued;
				}
			}

			queue.remove(current);
			explored.add(current);

			if (current.isTouching(goal)) {
				//System.out.println("FOUND PATH");
				return current;
			}

			for (GraphNode neighbor : current.getNeighbors()) {
				if (explored.contains(neighbor)) {
					continue;
				}

				int newCost = current.gCost + neighbor.distanceFrom(current);
				if (newCost < neighbor.gCost || !explored.contains(neighbor)) {
					neighbor.gCost = newCost;
					neighbor.hCost = neighbor.distanceFrom(goal);

					if (queue.contains(neighbor))
						queue.remove(neighbor);

					queue.add(neighbor);
				}
			}

			//System.out.println("CHECKED HERE");
		}

		return null;
	}

	class GraphNode {

		private GraphNode parent, goal;
		private int x, y;
		private int hCost, gCost;
		private String directionToParent;
		private int width, height;

		public GraphNode(GraphNode goal, int x, int y) {
			this.goal = goal;
			this.x = x;
			this.y = y;
		}

		public GraphNode(GraphNode goal, int x, int y, int width, int height) {
			this(goal, x, y);
			this.width = width;
			this.height = height;
		}

		public GraphNode(GraphNode parent, GraphNode goal, int x, int y, String directionToParent) {
			this(goal, x, y);
			this.parent = parent;
			this.directionToParent = directionToParent;
		}

		public GraphNode getParent() {
			return parent;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof GraphNode)) {
				return false;
			}

			GraphNode node = (GraphNode) o;
			return node.getX() == this.x && node.getY() == this.y;
		}

		public ArrayList<GraphNode> getNeighbors() {
			ArrayList<GraphNode> neighbors = new ArrayList<GraphNode>();

			if (x < LoveHunterXX.qtts.getWidth() - 5
					&& LoveHunterXX.qtts.canPlace(x, y, "east", KevinEnemy.this)) {
				neighbors.add(new GraphNode(this, goal, x + 5, y, "east"));
			}

			if (x - 5 > 0 && LoveHunterXX.qtts.canPlace(x, y, "west", KevinEnemy.this)) {
				neighbors.add(new GraphNode(this, goal, x - 5, y, "west"));
			}

			if (y < LoveHunterXX.qtts.getHeight() - 5
					&& LoveHunterXX.qtts.canPlace(x, y, "south", KevinEnemy.this)) {
				neighbors.add(new GraphNode(this, goal, x, y + 5, "south"));
			}

			if (y - 5 > 0 && LoveHunterXX.qtts.canPlace(x, y, "north", KevinEnemy.this)) {
				neighbors.add(new GraphNode(this, goal, x, y - 5, "north"));
			}

			//System.out.println(neighbors.size());
			//System.out.println(
			//		"X: " + x + " Y: " + y + " GoalX: " + goal.x + " GoalY: " + goal.y + " Dir: " + directionToParent);
			return neighbors;
		}

		public int distanceFrom(GraphNode node) {
			int dx = Math.abs(this.getX() - node.getX());
			int dy = Math.abs(this.getY() - node.getY());

			return 10 * (dx + dy);
		}

		public boolean isTouching(GraphNode node) {
			return Math.abs(this.getX() - goal.getX()) * 2 < KevinEnemy.this.getWidth() + goal.getWidth()
					&& Math.abs(this.getY() - goal.getY()) * 2 < KevinEnemy.this.getHeight() + goal.getHeight();
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public int fCost() {
			return gCost + hCost;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

	}

	@Override
	public void die() {
		LoveHunterXX.ts.lovePoints++;
		LoveHunterXX.qtts.kill(this);
	}

}
