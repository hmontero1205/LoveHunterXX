package quenchTheThirst;

import java.util.ArrayList;

public class Enemy extends LivingEntity {

	private String direction;

	public Enemy(int x, int y) {
		super(x, y, 0.025, "resources/robbierotten.png");
	}

	public void tick() {
		if (ShooterGame.shooterScreen == null) {
			return;
		}
		
		GraphNode goal = new GraphNode(null, ShooterGame.shooterScreen.getPlayer().getCenterX(),
				ShooterGame.shooterScreen.getPlayer().getCenterY());
		for (Entity e : ShooterGame.shooterScreen.getEntities()) {
			if (e instanceof AlluringBottle && e.distance(e, 10) && ((AlluringBottle) e).isActive()) {
				goal = new GraphNode(null, e.getCenterX(), e.getCenterY(), e.getWidth(), e.getHeight());
				break;
			}
		}
		

		GraphNode decision = findShortestPath(goal);
		if (decision == null || decision.getParent() == null) {
			System.out.println("NO DECISION");
			return;
		}

		while (decision.getParent().getParent() != null) {
			decision = decision.getParent();
		}

		direction = decision.directionToParent;
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

			if (x < ShooterGame.shooterScreen.getWidth() - 5
					&& ShooterGame.shooterScreen.canPlace(x + 5, y, getWidth(), getHeight(), Enemy.this)) {
				neighbors.add(new GraphNode(this, goal, x + 5, y, "east"));
			}

			if (x - 5 > 0 && ShooterGame.shooterScreen.canPlace(x - 5, y, getWidth(), getHeight(), Enemy.this)) {
				neighbors.add(new GraphNode(this, goal, x - 5, y, "west"));
			}

			if (y < ShooterGame.shooterScreen.getHeight() - 5
					&& ShooterGame.shooterScreen.canPlace(x, y + 5, getWidth(), getHeight(), Enemy.this)) {
				neighbors.add(new GraphNode(this, goal, x, y + 5, "south"));
			}

			if (y - 5 > 0 && ShooterGame.shooterScreen.canPlace(x, y - 5, getWidth(), getHeight(), Enemy.this)) {
				neighbors.add(new GraphNode(this, goal, x, y - 5, "north"));
			}
			
			System.out.println("X: " + x + " Y: " + y + " GoalX: " + goal.x + " GoalY: " + goal.y + " Dir: " + directionToParent);
			return neighbors;
		}
		
		public int distanceFrom(GraphNode node) {
			int dx = Math.abs(this.getX() - node.getX());
			int dy = Math.abs(this.getY() - node.getY());

			return 10 * (dx + dy);
		}
		
		public boolean isTouching(GraphNode node) {
			return Math.abs(this.getX() - goal.getX()) * 2 < Enemy.this.getWidth() + goal.getWidth()
					&& Math.abs(this.getY() - goal.getY()) * 2 < Enemy.this.getHeight() + goal.getHeight();
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight(){
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
		ShooterGame.shooterScreen.kill(this);
	}

}
