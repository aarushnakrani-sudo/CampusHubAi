package com.campushub.datastructure;

import com.campushub.model.NotesSubmission;

import java.util.Stack;

public class ApprovalStack {

    private Stack<NotesSubmission> stack;

    public ApprovalStack() {

        stack = new Stack<>();

    }

    // Push Note
    public void push(NotesSubmission submission) {

        stack.push(submission);

    }

    // Pop Note
    public NotesSubmission pop() {

        if (stack.isEmpty()) {

            return null;

        }

        return stack.pop();

    }

    // Top Note
    public NotesSubmission peek() {

        if (stack.isEmpty()) {

            return null;

        }

        return stack.peek();

    }

    // Empty?
    public boolean isEmpty() {

        return stack.isEmpty();

    }

    // Size
    public int size() {

        return stack.size();

    }

}