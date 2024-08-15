package io.github.vasilyrylov.archsample.feature.root.root_domain

import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.action.RootFSMAction
import junit.framework.TestCase.assertTrue
import ru.kontur.mobile.visualfsm.tools.VisualFSM
import ru.kontur.mobile.visualfsm.tools.graphviz.preset.DotAttributesDefaultPreset
import kotlin.test.Test

class RootFSMTests {
    @Test
    fun generateDigraph() {
        println(
            VisualFSM.generateDigraph(
                baseAction = RootFSMAction::class,
                baseState = RootFSMState::class,
                initialState = RootFSMState.AsyncWorkState.Initial::class,
                attributes = DotAttributesDefaultPreset(RootFSMState.AsyncWorkState::class)
            )
        )
        assertTrue(true)
    }

    @Test
    fun allStatesReachableTest() {
        val notReachableStates = VisualFSM.getUnreachableStates(
            baseAction = RootFSMAction::class,
            baseState = RootFSMState::class,
            initialState = RootFSMState.AsyncWorkState.Initial::class,
        )

        assertTrue(
            "FSM have unreachable states: ${notReachableStates.joinToString(", ")}",
            notReachableStates.isEmpty()
        )
    }

    @Test
    fun noFinalStateTest() {
        val finalStates = VisualFSM.getFinalStates(
            baseAction = RootFSMAction::class,
            baseState = RootFSMState::class,
        )

        assertTrue(
            "FSM have not correct final states: ${finalStates.joinToString(", ")}",
            finalStates.isEmpty()
        )
    }
}