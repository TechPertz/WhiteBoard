package org.example.javaproj.backend.model;

import org.apache.logging.log4j.LogManager;
import org.example.javaproj.backend.Constants;

import java.time.Instant;

public class Board {
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    private Long id;

    private Long ownerId;

    private String matrixData;

    private Instant dateCreated;

    public Board() {
    }

    public Board(User owner) {
        this.ownerId = owner.getId();
        this.matrixData = getDefaultBoard();
    }

    private String getDefaultBoard() {
        return "0".repeat(Constants.RESOLUTION_WIDTH * Constants.RESOLUTION_HEIGHT);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getMatrixData() {
        return matrixData;
    }

    public void setMatrixData(String matrixData) {
        this.matrixData = matrixData;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void updateBoardPixels(DrawingMessage drawingMessage) {
        for (DrawingPoint point : drawingMessage.getPoints()) {
            this.updatePixel(point.getX(), point.getY(), point.getPen());
        }
    }

    public void updatePixel(int x, int y, int val) {
        int index = (y * Constants.RESOLUTION_WIDTH) + x;
        if (index < 0 || index >= this.getMatrixData().length()) {
            LOGGER.error("Invalid coordinates: x = {}, y = {}, index = {}", x, y, index);
            return;
        }
        LOGGER.info("Valid coordinates: x = {}, y = {}", x, y);

        char[] matrixArray = this.getMatrixData().toCharArray();
        matrixArray[index] = (char) ('0' + val); // Convert integer val to char ('0' or '1')
        this.setMatrixData(new String(matrixArray));
    }
}
