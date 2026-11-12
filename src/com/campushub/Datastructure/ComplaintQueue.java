package com.campushub.datastructure;

import com.campushub.model.Complaint;

import java.util.LinkedList;
import java.util.Queue;

public class ComplaintQueue {

    private Queue<Complaint> complaintQueue;

    public ComplaintQueue() {

        complaintQueue = new LinkedList<>();

    }

    // Add Complaint
    public void enqueue(Complaint complaint) {

        complaintQueue.offer(complaint);

    }

    // Remove Complaint
    public Complaint dequeue() {

        if (complaintQueue.isEmpty()) {

            return null;

        }

        return complaintQueue.poll();

    }

    // View First Complaint
    public Complaint peek() {

        return complaintQueue.peek();

    }

    // Get All Complaints
    public Queue<Complaint> getQueue() {

        return complaintQueue;

    }

    // Check Empty
    public boolean isEmpty() {

        return complaintQueue.isEmpty();

    }

    // Size
    public int size() {

        return complaintQueue.size();

    }
    // Clear Queue
    public void clear() {

        complaintQueue.clear();

    }

}