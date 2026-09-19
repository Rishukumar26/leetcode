class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest point inside the rectangle to the circle's center
        int nearestX = Math.max(x1, Math.min(xCenter, x2));
        int nearestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate squared distance from center to nearest point
        int dx = nearestX - xCenter;
        int dy = nearestY - yCenter;
        
        return (dx * dx + dy * dy) <= (radius * radius);
    }
}